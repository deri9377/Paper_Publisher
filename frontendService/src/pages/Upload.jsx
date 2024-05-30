import Form from 'react-bootstrap/Form'
import { Button, FormControl, ListGroup } from "react-bootstrap";
import { useRef , useState } from "react";
import client from 'axios';
import {useNavigate} from 'react-router-dom';
import PathConstants from '../routes/pathConstants';


const Upload = () => {
    const [title, setTitle] = useState('')
    const [file, setFile] = useState()
    const username = localStorage.getItem('user')
    const userId = localStorage.getItem('userId')
    const paper =  useRef([])

    const navigate = useNavigate();

    const submitPost = async () => {
        const user = {name: username,id: userId}
        client.post(PathConstants.SERVER + '/paper', {
            user: user,
            file: file,
            title: title
        }).then((response) => {
            console.log(response.data.id)
            client.post(PathConstants.SERVER + '/post', {
                user: user,
                paper: {
                    user: user,
                    id: response.data.id,
                    title: title,
                    file: file
                }
            }).then((response) => {
                console.log(response.data)
                return response.data
            })
        }).finally(
            navigate("/posts")
        )
    }

    const convertToBase64 = (file) => {
        return new Promise((resolve, reject) => {
          const fileReader = new FileReader();
          fileReader.readAsDataURL(file);
          fileReader.onload = () => {
            resolve(fileReader.result);
          };
          fileReader.onerror = (error) => {
            reject(error);
          };
        });
      };
      
      const handleFileUpload = async (e) => {
        const file = e.target.files[0];
        const base64 = await convertToBase64(file);
        setFile(base64)
      };


    return (
        <div>
            <h3>
                Upload your paper here.
            </h3>
            <Form onSubmit={submitPost}>
                <Form.Group className="mb-3" controlId="formBasicUser">
                <Form.Label>Title</Form.Label>
                <Form.Control name='title' 
                    type="text" 
                    placeholder="Title" 
                    value={FormData.title} 
                    onChange={(e) => {setTitle(e.target.value)}}/>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>file</Form.Label>
                <Form.Control 
                    name="file" 
                    type="file" 
                    value={FormData.file} 
                    onChange={handleFileUpload}/>
                </Form.Group>
                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
        </div>
    )
}


export default Upload