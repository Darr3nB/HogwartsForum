import {useEffect, useState} from "react";
import MenuLayout from "../components/MenuLayout.jsx";
import Footer from "../components/Footer.jsx";
import {utility} from "../utility.js";


export default function AllQuestions() {
    const [allQuestions, setAllQuestionstate] = useState([]);

    // TODO Fetch questions
    const askedQuestions = async () => {
        const data = await utility.apiGet(`/api/get-all-questions`).then(response => response.json());
        // TODO In case of bad response, use state
        setAllQuestionstate(data);
    };

    useEffect(() => {
        askedQuestions().then();
    }, []);

    return (
        <div>
            <MenuLayout/>

            <h1>All questions</h1>

            <div id="all-questions-page-questions">
                {allQuestions.length <= 0
                    ? (<p>There are no asked questions yet.</p>)
                    : (<table>
                        <thead>
                        <tr>
                            <th>Latest question(s)</th>
                        </tr>
                        </thead>
                        <tbody>

                        {allQuestions.map(question => {
                            return (<tr key={"question-id-" + question.id}>
                                <td id={"question-id-" + question.id}>{question.title + " " + question.questionText}</td>
                            </tr>)
                        })}
                        </tbody>
                    </table>)}
            </div>

            <Footer/>
        </div>);
}
