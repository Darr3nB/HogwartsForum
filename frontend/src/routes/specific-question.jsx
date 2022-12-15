import {useEffect, useState} from "react";
import MenuLayout from "../components/MenuLayout.jsx";
import Footer from "../components/Footer.jsx";
import {useParams} from "react-router-dom";
import {utility} from "../utility.js";

export default function SpecificQuestion() {
    const {id} = useParams();
    const [question, setQuestionState] = useState(null);

    useEffect(() => {
        const getSpecificQuestion = async () => {
            let response = await utility.apiGet(`/api/get-specific-question/${id}`);
            let data = await response.json();
            setQuestionState(data);
        }
        getSpecificQuestion().then();
    }, [id]);

    if (question === null) {
        return (
            <div>
                <MenuLayout/>

                <div>Loading, please wait</div>

                <Footer/>
            </div>
        );
    }

    return (
        <div>
            <MenuLayout/>

            <div>
                <h2>{question.title}</h2>
                <div id="question-text">{question.questionText}</div>
                <div id="question-submission-time">{question.submissionTime}</div>
                <div id="comments">{question.commentList && question.commentList.map(comment => {
                    return (
                        <div key={"comment-id-" + comment.id}>
                            <div>{comment.comment}</div>
                            <span>{comment.submissionTime}</span>
                        </div>
                    );
                })}</div>
            </div>

            <Footer/>
        </div>
    );
}
