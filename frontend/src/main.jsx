import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import ErrorPage from "./error-page.jsx";
import {createBrowserRouter, RouterProvider, Route,} from "react-router-dom";
import Index from "./routes/index.jsx";

const router = createBrowserRouter([
    {
        path: "/",
        element: <Index/>,
        errorElement: <ErrorPage/>,
        // children: [
        //     {
        //         path: "contacts/:contactId",
        //         element: <Contact/>,
        //         loader: contactLoader,
        //     },
        // ],
    },
]);

ReactDOM.createRoot(document.getElementById("root")).render(
    <React.StrictMode>
        <RouterProvider router={router}/>
    </React.StrictMode>
);
