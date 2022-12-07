import {useState} from "react";
import MenuLayout from "../components/MenuLayout.jsx";
import Footer from "../components/Footer.jsx";

export default function Index() {
    const [questions, setQuestionState] = useState([]);

    // Fetch questions
    const askedQuestions = async () => {
        const data = await fetch(`http://localhost:8080/api/five-latest-question`).then(response => response.json());
        // TODO In case of bad response, use state
        setQuestionState(data);
    };
    // TODO useEffect maybe? If new question posted, automatically refresh the data

    return (
        <div className="App">
            <MenuLayout/>
            <h1>Welcome to Hogwarts forum!</h1>
            <button type="button">Ask a question</button>
            {/*Put a button here that route to all questions*/}
            <p>Find friends, chat, connect with other students, ask questions or help in studies.</p>
            <div id="main-page-questions">
                {questions.length <= 0
                    ? (<p>There are no asked questions yet.</p>)
                    : (<table>
                        <tr>
                            <th>Latest question(s)</th>
                        </tr>
                        {questions.map((question, index) => {
                            return (<tr>
                                <td id={"question-id-" + question.id}>{question.title + " " + question.questionText}</td>
                            </tr>)
                        })}
                    </table>)}
            </div>
            <Footer/>
        </div>
    );
}