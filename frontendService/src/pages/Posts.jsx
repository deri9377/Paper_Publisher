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

const Post = () => {
    const [posts, setPosts] = useState([]);
    const [commentMessage, setCommentMessage] = useState({
        postId:'',
        message: ''
    });
    const userName = useSelector((state) => state.user.name)
    const userId = useSelector((state) => state.user.id)
    console.log("UserName: " + userName)
    console.log("UserID: " + userId)

    useEffect(() => {
        const fetchPosts = async () => {
            const response = await client.get('http://localhost:8080/posts');
            setPosts(response.data);
        };
        fetchPosts();   
    }, []);

    const postComment = (e) => {
      const {name, value} = e.target;
      setCommentMessage(prevState => ({
        ...prevState,
        [name]:value
      }));
      sendPost()
    }

    const sendPost = async () => {
      let response = await client.post('http://localhost:8080/post/' + commentMessage.postId + '/comment', {
        user: {
          name: userName,
          id: userId
        },
        message: commentMessage.message
      })
      console.log(response.data)
    }

  return (

    <div className="app">
        <h1>Posts</h1>
        <div className="users-container" >
        <Stack>
        {posts.map((post) => {
            return (
              <Accordion key={post.id}>
                <Accordion.Item eventKey="0">
                  <Accordion.Header>
                    {post.paper.title}
                    <br/>
                    Posted By: {post.user.name}
                  </Accordion.Header>
                  <Accordion.Body>
                  <div style={{overflowWrap:"break-word", width:"1100px"}}>
                    {post.paper.file}
                  </div>
                    <ListGroup>
                        {AllCollapse(post.id, post.comments)}
                    </ListGroup>
                    <Form>
                      <Form.Control type="hidden" value={post.id}/>
                      <Form.Control type="text" placeholder="New Comment" name="message" value={FormData.message} onSubmit={postComment} />
                      <Button variant="primary" type="submit">Add Comment</Button>
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