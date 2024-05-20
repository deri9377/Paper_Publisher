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


function Login() {
  const count = useSelector((state) => state.user.name)
  const dispatch = useDispatch()
  const navigate = useNavigate();



  const [loginData, setLoginData] = useState({
    username: '',
    password: '',
  });

  const [createUserData, setCreateUserData] = useState({
    fullname: '',
    username: '',
    password: ''
  });


  const getUserByName = async (name) => {
    let response = await client.get("http://localhost:8080/user/" + name);
    dispatch(setUser(response.data.name, response.data.id))
};

  const handleLoginSubmit = (e) => {
    const { name, value } = e.target;
    setLoginData(prevState => ({
      ...prevState,
      [name]: value,
    }));
    getUserByName(loginData.username)
    localStorage.setItem('user', loginData.username)
  }

  const handleUserCreateSubmit = (e) => {
    const { name, value } = e.target;
    setCreateUserData(prevState => ({
      ...prevState,
      [name]: value,
    }));
    getUserByName(createUserData.username)
    navigate('/users')
  }


  return (
    <div>
    <Tabs
      defaultActiveKey="login"
      id="uncontrolled-tab-example"
      className="mb-3"
    >
      <Tab eventKey="login" title="Login">
        <Form>
          <Form.Group className="mb-3" controlId="formBasicUser">
            <Form.Label>Username</Form.Label>
            <Form.Control name='username' type="text" placeholder="Username" value={FormData.username} onSubmit={handleLoginSubmit}/>
          </Form.Group>

          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control name='password' type="password" placeholder="Password" value={FormData.password} onSubmit={handleLoginSubmit}/>
          </Form.Group>
        <Button variant="primary" type="submit">
          Submit
        </Button>
    </Form>
      </Tab>
      <Tab eventKey="create user" title="Create User">
      <Form>
          <Form.Group className="mb-3" controlId="formBasicName">
            <Form.Label>Name</Form.Label>
            <Form.Control type="text" placeholder="Name" name="name" value={FormData.fullname} onSubmit={handleUserCreateSubmit}/>
          </Form.Group>
          <Form.Group className="mb-3" controlId="formBasicUser">
            <Form.Label>Username</Form.Label>
            <Form.Control type="text" placeholder="Username" name='username'  value={FormData.username} onSubmit={handleUserCreateSubmit}/>
          </Form.Group>
          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control type="password" placeholder="Password" name='password' value={FormData.password} onSubmit={handleUserCreateSubmit}/>
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
