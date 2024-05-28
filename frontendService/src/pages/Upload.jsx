import Form from 'react-bootstrap/Form'
import { Button, FormControl, ListGroup } from "react-bootstrap";
import { useEffect , useState } from "react";
import client from 'axios';
import PathConstants from '../routes/pathConstants';


const Upload = () => {
    const [paper, setPaper] = useState('')
    const [file, setFile] = useState()
    const username = localStorage.getItem('user')
    const userId = localStorage.getItem('userId')

    const paperSubmit = () => {
        const submitPaper = async () => {
            const response = await client.post(PathConstants.SERVER + '/paper', {
                {
                    user: {
                        name: username,
                        id: userId
                    },
                    title: paper
                },
                file: file
            })
        }
        submitPaper()
    }

    return (
        <div>
            {paper.title}
            {paper.filename}
            <h3>
                Upload your paper here.
            </h3>
            <Form>
                <Form.Group className="mb-3" controlId="formBasicUser">
                <Form.Label>Title</Form.Label>
                <Form.Control name='title' 
                    type="text" 
                    placeholder="Title" 
                    value={FormData.title} 
                    onChange={(e) => {setPaper(e.target.value)}}/>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>file</Form.Label>
                <Form.Control 
                    name="file" 
                    type="file" 
                    value={FormData.file} 
                    onChange={(e) => {setFile(e.target.files[0])}}/>
                </Form.Group>
                <Button variant="primary" type="submit" onClick={paperSubmit}>
                    Submit
                </Button>
            </Form>
        </div>
    )
}


export default Upload