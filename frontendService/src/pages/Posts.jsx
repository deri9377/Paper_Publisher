import { useEffect , useState } from "react";
import client from 'axios';
import Accordion from 'react-bootstrap/Accordion';
import Stack from 'react-bootstrap/Stack'
import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react';
import Form from 'react-bootstrap/Form'
import { Button, FormControl, ListGroup } from "react-bootstrap";
import {useSelector} from 'react-redux'
import { useResolvedPath } from "react-router-dom";
import PathConstants from "../routes/pathConstants";

const Post = () => {
    const [posts, setPosts] = useState([]);
    const [commentMessage, setCommentMessage] = useState({
        postId:'',
        message: ''
    });
    const user = localStorage.getItem('user');
    const userId = localStorage.getItem('userId')

    useEffect(() => {
        const fetchPosts = async () => {
            const response = await client.get(PathConstants.SERVER + '/posts');
            setPosts(response.data);
        };
        fetchPosts();   
    }, []);

    const sendComment = async () => {
      let response = await client.post(PathConstants.SERVER + '/post/' + commentMessage.postId + '/comment', {
        user: {
          id: userId
        },
        message: commentMessage.message
      })
      console.log(response.data)
    }

    const changeHandler = (event) => {
      const name = event.target.name;
      const value = event.target.value;
      setCommentMessage(values => ({...values, [name]: value}))
    }

  return (

    <div className="app">
        <h1>Posts</h1>
        <div className="users-container" >
        <Stack>
        {posts.map((post) => {
            return (
              <Accordion key={post.id}>
                <Accordion.Item eventKey="0" value={post.id}>
                  <Accordion.Header>
                    {post.paper.title}
                    <br/>
                    Posted By: {post.user.name}
                  </Accordion.Header>
                  <Accordion.Body>
                  <div style={{overflowWrap:"break-word", width:"1100px"}}>
                    {atob(post.paper.file)}
                  </div>
                    <ListGroup>
                        {AllCollapse(post.id, post.comments)}
                    </ListGroup>
                    <Form onSubmit={sendComment}>
                      <Form.Control type="text" placeholder="New Comment" name="message" value={FormData.message} onChange={changeHandler} />
                      <Button variant="primary" name="postId" value={post.id} type="submit" onClick={changeHandler}>Add Comment</Button>
                    </Form>
                  </Accordion.Body>
                </Accordion.Item>
              </Accordion>
              );
          })}
        </Stack>
    </div>
   </div>
   
)};

function AllCollapse(id, comments) {
  return (
    <div>
      {comments.map((comment) => {
          return ( 
          <ListGroup.Item key={comment.id}>
            <p>{comment.text}</p>
            <footer>commented by {comment.user.name}</footer>
          </ListGroup.Item>
        )
        })}
    </div>
  );
}

export default Post