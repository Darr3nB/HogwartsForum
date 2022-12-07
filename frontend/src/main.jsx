import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import ErrorPage from "./error-page.jsx";
import {createBrowserRouter, RouterProvider, Route,} from "react-router-dom";
import Index from "./routes/index.jsx";
import AllQuestions from "./routes/allQuestions.jsx";
import Registration from "./routes/registration.jsx";
import PostQuestion from "./routes/postQuestion.jsx";

const router = createBrowserRouter([
    {
        path: "/",
        element: <Index/>,
        errorElement: <ErrorPage/>,
    },
    {
        path: "/all-questions",
        element: <AllQuestions/>,
    },
    {
        path: "/registration",
        element: <Registration/>,
    },
    {
        path: "/post-question",
        element: <PostQuestion/>,
    },
]);

ReactDOM.createRoot(document.getElementById("root")).render(
    <React.StrictMode>
        <RouterProvider router={router}/>
    </React.StrictMode>
);
