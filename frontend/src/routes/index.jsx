import {useEffect, useState} from "react";
import MenuLayout from "../components/MenuLayout.jsx";
import Footer from "../components/Footer.jsx";
import {Link} from "react-router-dom";
import {utility} from "../utility.js";

export default function Index() {
    const [topFiveQuestions, setTopFiveQuestionState] = useState([]);

    const askedQuestions = async () => {
        const data = await utility.apiGet(`/api/five-latest-question`).then(response => response.json());
        // TODO In case of bad response, use state
        setTopFiveQuestionState(data);
    };
    // TODO useEffect maybe? If new question posted, automatically refresh the data

    useEffect(() => {
        askedQuestions().then();
    }, []);


    return (
        <div className="App">
            <MenuLayout/>

            <h1>Welcome to Hogwarts forum!</h1>

            <Link to={"/post-question"}><button type="button">Ask a question</button></Link>
            <Link to={`all-questions`}><button>All Questions</button></Link>
            <p>Find friends, chat, connect with other students, ask questions or help in studies.</p>

            <div id="main-page-questions">
                {topFiveQuestions.length <= 0
                    ? (<p>There are no asked questions yet.</p>)
                    : (<table>
                        <tr>
                            <th>Latest question(s)</th>
                        </tr>
                        {topFiveQuestions.map(question => {
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
