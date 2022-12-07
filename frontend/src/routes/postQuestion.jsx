import {useState} from "react";
import MenuLayout from "../components/MenuLayout.jsx";
import Footer from "../components/Footer.jsx";
import {Link} from "react-router-dom";

export default function PostQuestion() {
    // TODO check if logged in, case: no, redirect error
    return (
        <div>
            <MenuLayout/>

            <h1>Ask your question</h1>

            <form action="/post-question" method="post">
                <label htmlFor="question-title">Question title: </label>
                <input type="text" id="question-title" minLength="5"/>

                <label htmlFor="question-description">Description: </label>
                <textarea type="text" id="question-description" rows="4" cols="50" minLength="5"/>

                <Link to="post-question">
                    <button>Post question</button>
                </Link>
            </form>

            <Footer/>
        </div>
    );
}