import {useState} from "react";
import MenuLayout from "../components/MenuLayout.jsx";
import Footer from "../components/Footer.jsx";


export default function AllQuestions() {
    const [allQuestions, setAllQuestionstate] = useState([]);

    // TODO Fetch questions
    const askedQuestions = async () => {
        const data = await fetch(`http://localhost:8080/api/get-all-questions`).then(response => response.json());
        // TODO In case of bad response, use state
        setAllQuestionstate(data);
    };

    return (
        <div>
            <MenuLayout/>

            <h1>All questions</h1>

            <div id="all-questions-page-questions">
                {allQuestions.length <= 0
                    ? (<p>There are no asked questions yet.</p>)
                    : (<table>
                        <tr>
                            <th>Latest question(s)</th>
                        </tr>
                        {allQuestions.map(question => {
                            return (<tr>
                                <td id={"question-id-" + question.id}>{question.title + " " + question.questionText}</td>
                            </tr>)
                        })}
                    </table>)}
            </div>

            <Footer/>
        </div>);
}
