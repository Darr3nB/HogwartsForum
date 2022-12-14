import {useState} from "react";
import MenuLayout from "../components/MenuLayout.jsx";
import Footer from "../components/Footer.jsx";
import {useNavigate} from "react-router-dom";
import {utility} from "../utility.js";


async function postAQuestion(e, navigate) {
    e.preventDefault();
    const postQuestionData = new FormData(e.currentTarget);

    await utility.apiPostWithDictionaryDataType('/post-question',
        {
            'title': postQuestionData.get("question-title"),
            'questionText': postQuestionData.get("question-description")
        })
        .then(response => {
            if (response.ok) {
                navigate('/');
            }
        });
}

export default function PostQuestion() {
    const navigate = useNavigate();
    // TODO check if logged in, case: no, redirect error
    return (
        <div>
            <MenuLayout/>

            <h1>Ask your question</h1>

            <form onSubmit={event => postAQuestion(event, navigate)}>
                <label htmlFor="question-title">Question title: </label>
                <input type="text" id="question-title" name="question-title" minLength="5"/>

                <label htmlFor="question-description">Description: </label>
                <textarea id="question-description" name="question-description" rows="4" cols="50" minLength="5"/>

                <button type="submit" id="post-question-submit-button">Post question</button>
            </form>

            <Footer/>
        </div>
    );
}
