import {useEffect, useState} from "react";
import MenuLayout from "../components/MenuLayout.jsx";
import Footer from "../components/Footer.jsx";
import {utility} from "../utility.js";
import {useNavigate} from "react-router-dom";


export default function AllQuestions() {
    const navigate = useNavigate();
    const [allQuestions, setAllQuestionState] = useState([]);

    const askedQuestions = async () => {
        const data = await utility.apiGet(`/api/get-all-questions`).then(response => response.json());
        // TODO In case of bad response, use state
        setAllQuestionState(data);
    };

    const openSelectedQuestion = (e, id) => {
        navigate(`/specific-question/${id}`);
    };

    useEffect(() => {
        askedQuestions().then();
    }, []);

    return (
        <div>
            <MenuLayout/>

            <div className="slight-white-background">
                <h1 className="header-to-middle">All questions</h1>
            </div>

            <div id="all-questions-page-questions">
                {allQuestions?.length <= 0
                    ? (<p className="slight-white-background">There are no asked questions yet.</p>)
                    : (<table className="main-page-table">
                        <thead>
                        <tr>
                            <th>Latest question(s)</th>
                        </tr>
                        </thead>
                        <tbody>

                        {allQuestions?.map(question => {
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

            <Footer/>
        </div>);
}
