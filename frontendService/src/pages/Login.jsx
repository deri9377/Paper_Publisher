import { useState } from 'react'
import App from '../App.jsx'
import User from './User.jsx'
import './Login.jsx'
import 'bootstrap/dist/css/bootstrap.min.css';
import Nav from 'react-bootstrap/Nav';
import Tab from 'react-bootstrap/Tab';
import Tabs from 'react-bootstrap/Tabs';
import Button from 'react-bootstrap/Button';
import client from 'axios';
import Form from 'react-bootstrap/Form';
import { BrowserRouter as Router, Route, Link, Routes } from "react-router-dom";
import { setUser } from '../components/userSlice';
import { useSelector, useDispatch} from 'react-redux';
import {useNavigate} from 'react-router-dom';
import PathConstants from '../routes/pathConstants.jsx';


const Login = (props) => {

  const [password, setPassword] = useState('')
  const [username, setUserName] = useState('')

  const navigate = useNavigate();


  const [createUserData, setCreateUserData] = useState({
    fullname: '',
    username: '',
    password: ''
  });

  const createNewUser = async (name) => {
    let response = await client.post(PathConstants.SERVER + '/user', {
      name:name
    })
    console.log(response.data)
  }

  const getUserByName = async (name) => {
    let response = await client.get(PathConstants.SERVER + '/user/name/' + name);
    //console.log(response.data)
    localStorage.setItem('userId', response.data.id)
};

  const handleLoginSubmit = () => {
    localStorage.setItem('user', username);
    localStorage.setItem('loggedIn', true);
    getUserByName(username);
    navigate('/profile')
  }

  const handleUserCreateSubmit = () => {
    createNewUser(username)
    navigate('/login')
  }


  return (
    <div>
    <Tabs
      defaultActiveKey="login"
      id="uncontrolled-tab-example"
      className="mb-3"
    >
      <Tab eventKey="login" title="Login">
        <Form onSubmit={handleLoginSubmit}>
          <Form.Group className="mb-3" controlId="formBasicUser">
            <Form.Label>Username</Form.Label>
            <Form.Control name='username' 
                type="text" 
                placeholder="Username" 
                value={FormData.username} 
                onChange={(ev) => setUserName(ev.target.value)}/>
          </Form.Group>

          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control 
              name='password' 
              type="password" 
              placeholder="Password" 
              value={FormData.password} 
              onChange={(ev) => setPassword(ev.target.password)}/>
          </Form.Group>
        <Button variant="primary" type="submit">
          Submit
        </Button>
    </Form>
      </Tab>
      <Tab eventKey="create user" title="Create User">
      <Form onSubmit={handleUserCreateSubmit}>
          <Form.Group className="mb-3" controlId="formBasicName">
            <Form.Label>Name</Form.Label>
            <Form.Control type="text" placeholder="Name" name="name" value={FormData.fullname}/>
          </Form.Group>
          <Form.Group className="mb-3" controlId="formBasicUser">
            <Form.Label>Username</Form.Label>
            <Form.Control type="text" placeholder="Username" name='username'  value={FormData.username} onChange={(ev) => setUserName(ev.target.value)}/>
          </Form.Group>
          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control type="password" placeholder="Password" name='password' value={FormData.password} onChange={(ev) => setPassword(ev.target.password)}/>
          </Form.Group>
        <Button variant="primary" type="submit">
          Submit
        </Button>
    </Form>
      </Tab>
    </Tabs>
    </div>
  );


}

export default Login
