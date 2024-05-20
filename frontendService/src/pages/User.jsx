import { useEffect , useState } from "react";
import { Button } from 'react-bootstrap'
import client from 'axios';
import Accordion from 'react-bootstrap/Accordion';
import Stack from 'react-bootstrap/Stack'
import Form from 'react-bootstrap/Form'
import 'bootstrap/dist/css/bootstrap.min.css';

const User = () => {
    const [users, setUsers] = useState([]);
    const [name, setName] = useState('');


    useEffect(() => {
        const fetchUsers = async () => {
            const response = await client.get('http://localhost:8080/users');
            setUsers(response.data);
        };
        fetchUsers();   
    }, []);


  return (
    <div className="app">
        <h1>Users</h1>
        <div className="users-container">
        <Stack>
        {users.map((user) => {
            return (
                AllCollapse(user.id, user.name)
            );
        })}
        </Stack>
    </div>
   </div>
   
)};

function AllCollapse(id, name) {
  return (
    <div>
    <Accordion>
      <Accordion.Item eventKey="0">
        <Accordion.Header>{name}</Accordion.Header>
        <Accordion.Body>
          ID: {id}
        </Accordion.Body>
      </Accordion.Item>
    </Accordion>
    </div>
  );
}

export default User