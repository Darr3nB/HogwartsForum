import {Link, useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import {utility} from "../utility.js";

export default function LatestFiveQuestion() {
    const navigate = useNavigate();
    const [topFiveQuestions, setTopFiveQuestionState] = useState([]);

    const askedQuestions = async () => {
        const data = await utility.apiGet(`/api/five-latest-question`)
            .then(response => response.json());
        // TODO In case of bad response, use state
        setTopFiveQuestionState(data);
    };

    useEffect(() => {
        askedQuestions().then();
    }, []);

    const openSelectedQuestion = (e, id) => {
        navigate(`/specific-question/${id}`);
    };

    return (
        <div>
            <div className="slight-white-background">
                <h1 className="header-to-middle">Welcome to Hogwarts forum!</h1>
                <p className="header-to-middle">Find friends, chat, connect with other students, ask questions or help
                    in studies.</p>
            </div>

            <div className="question-buttons">
                <Link to={"/post-question"}>
                    <button type="button">Ask a question</button>
                </Link>
                <Link to={`all-questions`}>
                    <button>All Questions</button>
                </Link>
            </div>


            <div id="main-page-questions">
                {topFiveQuestions.length <= 0
                    ? (<p className="slight-white-background">There are no asked questions yet.</p>)
                    : (<table className="main-page-table">
                        <thead>
                        <tr>
                            <th>Latest question(s)</th>
                        </tr>
                        </thead>
                        <tbody>
                        {topFiveQuestions?.map(question => {
                            return (<tr key={"question-id-" + question?.id}>
                                <td id={"question-id-" + question?.id}
                                    onClick={event => openSelectedQuestion(event, question.id)}>
                                    {question?.title + " " + question?.questionText}
                                </td>
                            </tr>)
                        })}
                        </tbody>
                    </table>)}
            </div>
        </div>
    );
}