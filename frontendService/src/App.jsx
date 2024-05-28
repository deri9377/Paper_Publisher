import Home from './pages/Home.jsx'
import './App.css'
import User from './pages/User.jsx'
import Post from './pages/Posts.jsx'
import Login from './pages/Login.jsx'
import Profile from './pages/Profile.jsx'
import Upload from './pages/Upload.jsx'
import 'bootstrap/dist/css/bootstrap.min.css';
import Layout from "./components/Layout.jsx"
import { useState, useEffect } from "react";
import { BrowserRouter as Router, Route, Link, Routes, createBrowserRouter, RouterProvider } from "react-router-dom";

function App() {
  const [loggedIn, setLoggedIn] = useState(false)
  const [username, setUserName] = useState('')
  
  const router = createBrowserRouter([
    {
      element: <Layout/>,
      children: [
        {
          path: '/',
          element: <Home/>
        },
        {
          path: '/users',
          element: <User />
        },
        {
          path: '/login',
          element: <Login/>
        },
        {
          path: '/posts',
          element: <Post/>
        },
        {
          path: '/profile',
          element: <Profile/>
        },
        {
          path: '/upload',
          element: <Upload/>
        }
      ],
    }
  ])

  useEffect(() => {
    // Fetch the user email and token from local storage
    const user = localStorage.getItem('user');
  
    // If the token/email does not exist, mark the user as logged out
    if (!user || !user.token) {
      setLoggedIn(false)
      return
    } else {
      setLoggedIn(true)
      setUserName(user.username || '')
      return
    }
}, [])


  return (
    <div>
      <RouterProvider router={router}/>
    </div>
  );
}

export default App
