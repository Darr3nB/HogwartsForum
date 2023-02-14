import {utility} from "../utility.js";
import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";


export default function PostQuestion() {
    const navigate = useNavigate();
    const [loggedInUSer, setUser] = useState(false);

    useEffect(() => {
        utility.loggedInUser().then(
            d => {
                if (d === false) {
                    navigate("/");
                } else {
                    setUser(d);
                }
            }
        );
    }, []);

    const postAQuestion = async (e) => {
        e.preventDefault();
        const postQuestionData = new FormData(e.currentTarget);

        await utility.apiPostWithDictionaryDataType(`/question/post-question/${loggedInUSer.id}`,
            {
                'title': postQuestionData.get("question-title"),
                'questionText': postQuestionData.get("question-description"),
                'image': utility.questionMarkPicture // TODO replace with uploaded img
            })
            .then(response => {
                if (response.ok) {
                    navigate('/');
                }
            });
    }

    return (
        <div>
            <div className="slight-white-background">
                <h1 className="header-to-middle">Ask your question</h1>
            </div>

            <div className="ask-question-div">
                <form onSubmit={event => postAQuestion(event)}>
                    <label htmlFor="question-title" className="reg-fields">Question title: </label>
                    <input type="text" id="question-title" name="question-title" minLength="5" className="reg-fields"/>

                    <label htmlFor="question-description" className="reg-fields">Description: </label>
                    <textarea id="question-description" name="question-description" rows="4" cols="50" minLength="5"
                              className="reg-fields"/>

                    <button type="submit" id="post-question-submit-button" className="button-to-middle">Post question
                    </button>
                </form>
            </div>
        </div>
    );
}
