import {utility} from "../utility.js";
import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";


export default function PostQuestion() {
    const navigate = useNavigate();
    const [loggedInUSer, setUser] = useState(false);
    const [uploadedImage, setImage] = useState(null);

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
        const questionPicture = uploadedImage === null ? utility.questionMarkPicture : uploadedImage;

        await utility.apiPostWithDictionaryDataType(`/question/post-question/${loggedInUSer.id}`,
            {
                'title': postQuestionData.get("question-title"),
                'questionText': postQuestionData.get("question-description"),
                'image': questionPicture
            })
            .then(response => {
                if (response.ok) {
                    navigate('/');
                }
            });
    }

    const uploadImage = async (event) => {
        const file = event.target.files[0];
        const base64 = await utility.convertBase64(file).then();

        setImage(base64);
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
                    <div className="header-to-middle"><input type="file" id="input-for-file-on-post-question" name="input-for-file-on-post-question"
                                accept=".jpg, .jpeg, .png" onChange={(event) => {
                    uploadImage(event);
                }}/></div>

                    <br/><button type="submit" id="post-question-submit-button" className="button-to-middle">Post question
                    </button>
                </form>
            </div>
        </div>
    );
}
