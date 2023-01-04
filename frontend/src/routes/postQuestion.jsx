import MenuLayout from "../components/MenuLayout.jsx";
import Footer from "../components/Footer.jsx";
import {useNavigate} from "react-router-dom";
import {utility} from "../utility.js";
import {useEffect, useState} from "react";


async function postAQuestion(e, navigate) {
    e.preventDefault();
    const postQuestionData = new FormData(e.currentTarget);

    await utility.apiPostWithDictionaryDataType('/question/post-question',
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
    const [isLoggedIn, setLoginState] = useState(false);
    // TODO check if logged in, case: no, redirect error
    useEffect(() => {
        utility.isLoggedInRequest().then(
            d => {
                if (d === false) {
                    setLoginState(false);
                    navigate("/");
                } else {
                    setUser(d);
                    setLoginState(true);
                }
            }
        );
    }, [isLoggedIn]);

    return (
        <div>
            <MenuLayout/>

            <div className="slight-white-background">
                <h1 className="header-to-middle">Ask your question</h1>
            </div>

            <div className="ask-question-div">
                <form onSubmit={event => postAQuestion(event, navigate)}>
                    <label htmlFor="question-title" className="reg-fields">Question title: </label>
                    <input type="text" id="question-title" name="question-title" minLength="5" className="reg-fields"/>

                    <label htmlFor="question-description" className="reg-fields">Description: </label>
                    <textarea id="question-description" name="question-description" rows="4" cols="50" minLength="5" className="reg-fields"/>

                    <button type="submit" id="post-question-submit-button" className="button-to-middle">Post question</button>
                </form>
            </div>

            <Footer/>
        </div>
    );
}
