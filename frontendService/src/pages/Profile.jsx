import Accordion from 'react-bootstrap/Accordion';
import Stack from 'react-bootstrap/Stack'
import { useEffect , useState } from "react";
import client from 'axios';
import {ListGroup } from "react-bootstrap";
import PathConstants from "../routes/pathConstants";

const Profile = () => {

    var loggedIn = localStorage.getItem('loggedIn')
    const user = localStorage.getItem('user')
    const userId = localStorage.getItem('userId')
    const [posts, setPosts] = useState([]);
    const [comments, setComments] = useState([])




    useEffect(() => {
        const fetchPosts = async () => {
            const response = await client.get(PathConstants.SERVER + '/posts/user/' + userId);
            setPosts(response.data);
        };
        const fetchComments = async() => {
            const response = await client.get(PathConstants.SERVER + '/user/' + userId + '/comments')
            setComments(response.data)
        }
        fetchPosts();
        fetchComments();
        console.log(userId)
    }, []);

    async function getPostById(id) {
        let response = await client.get(PathConstants.SERVER + '/post/' + id)
        console.log(response.data.paper.title)
        return <div>{response.data.paper.title}</div>
    }

    return (
        <div>
            <h1>
                Hello {user}, Welcome to your profile.
            </h1>
            id: {userId}
            <h3>Posts</h3>
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
                    </Accordion.Body>
                    </Accordion.Item>
                </Accordion>
                );
            })}

            <h3>Comments</h3>
            {comments.map((comment) => {
                return(
                    <div key={comment.id}>
                        <div>
                            {getPostById(comment.postId)}
                        </div>
                        Comment Text: {comment.text}
                        <br/>
                    </div>
                    )
            })}

        </div>
    )
}

export default Profile