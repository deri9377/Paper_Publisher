import PathConstants from "../routes/pathConstants";
import Nav from 'react-bootstrap/Nav'
import 'bootstrap/dist/css/bootstrap.min.css';

const Header = (props) => {

    return (
        <header>
            <Nav variant="pills" defaultActiveKey="/home">
                <Nav.Item>
                    <Nav.Link href={PathConstants.HOME}>Home</Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link href={PathConstants.UPLOAD}>Upload</Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link href={PathConstants.POSTS}>Posts</Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    {props.loggedIn? <Nav.Link href={PathConstants.PROFILE}>logged in as {props.user}</Nav.Link> : <Nav.Link href={PathConstants.LOGIN}>Login</Nav.Link>}
                </Nav.Item>
            </Nav>
        </header>
    )
}

export default Header;