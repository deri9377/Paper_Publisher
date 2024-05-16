import Home from './pages/Home.jsx'
import './App.css'
import User from './pages/User.jsx'
import Post from './pages/Posts.jsx'
import Login from './pages/Login.jsx'
import 'bootstrap/dist/css/bootstrap.min.css';
import Layout from "./components/Layout.jsx"
import { BrowserRouter as Router, Route, Link, Routes, createBrowserRouter, RouterProvider } from "react-router-dom";

function App() {
  
  const router = createBrowserRouter([
    {
      element: <Layout/>,
      children: [
        {
          path: '/',
          element: <Home />
        },
        {
          path: '/users',
          element: <User />
        },
        {
          path: '/login',
          element: <Login />
        },
        {
          path: '/posts',
          element: <Post/>
        }
      ],
    }
  ])


  return (
    <div>
      <RouterProvider router={router}/>
    </div>
  );
}

export default App
