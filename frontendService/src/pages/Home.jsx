import 'bootstrap/dist/css/bootstrap.min.css';
import { useNavigate } from 'react-router-dom'
import { useState, useEffect } from "react";

const Home = () => {
    var loggedIn = localStorage.getItem('loggedIn')
    
    const username = localStorage.getItem('user')
    const navigate = useNavigate()


    const onButtonClick = () => {
        console.log(loggedIn, localStorage.getItem('loggedIn'))
        if (loggedIn) {
            localStorage.removeItem('loggedIn')
            localStorage.removeItem('user')
            window.location.reload();
            navigate('/')
        } else {
            navigate('/login')
        }

    }

    return (
        <div>
        <h1>Welcome to Paper Publisher!</h1>
        <div>This is the home page.</div>
        <div className={'buttonContainer'}>
          <input
            className={'inputButton'}
            type="button"
            onClick={onButtonClick}
            value={loggedIn? 'Log out' : 'Log in'}
          />
          {loggedIn? <div>you are logged in as {username}</div> : <div />}
        </div>
      </div>
    )
}


export default Home