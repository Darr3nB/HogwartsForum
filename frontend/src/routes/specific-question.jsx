import {useEffect, useState} from "react";
import MenuLayout from "../components/MenuLayout.jsx";
import Footer from "../components/Footer.jsx";
import {useNavigate, useParams} from "react-router-dom";
import {utility} from "../utility.js";

export default function SpecificQuestion() {
    const {id} = useParams();
    const [question, setQuestionState] = useState(null);
    const [commentDiv, setCommentDivState] = useState(false);
    const [isLoggedIn, setLoginState] = useState(false);
    const [loggedInUSer, setUser] = useState({});
    const navigate = useNavigate();
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

                <div className="loading-field"></div>
                <div className="slight-white-background">Loading, please wait, the magic is happening.</div>

                <Footer/>
            </div>
        );
    }

    const showAndHideCommentField = (event) => {
        // TODO prevent this if logged out
        event.preventDefault();
        setCommentDivState(!commentDiv);
    }

    const postComment = async (event) => {
        event.preventDefault();
        const componentData = new FormData(event.currentTarget);

        await utility.apiGet(
            `/api/post-comment-on-specific-question/${question.id}/${loggedInUSer.id}/${componentData.get("comment-text-area")}`
        )
            .then(response => {
                if (response.ok) {
                    navigate(0);
                }
            })
    }

    const doUpvote = async (event, commentId) => {
        event.preventDefault();

        await utility.apiGet(`/api/upvote-comment/${commentId}`)
            .then(response => {
                if (response.ok) {
                    navigate(0);
                }
            });
    }

    const doDownvote = async (event, commentId) => {
        event.preventDefault();

        await utility.apiGet(`/api/downvote-comment/${commentId}`)
            .then(response => {
                if (response.ok) {
                    navigate(0);
                }
            });
    }

    const deleteComment = async (event, commentId) => {
        event.preventDefault();

        await utility.apiDeleteWithPathData(`/api/delete-comment/${commentId}`)
            .then(response => {
                if (response.ok){
                    navigate(0);
                }
            });
    }

    return (
        <div>
            <MenuLayout/>

            <div className="specific-question-page">

                <div className="slight-white-background">
                    <h2 className="header-to-middle">{question?.title}</h2>
                </div>

                <div className="slight-white-background">
                    <div id="question-text"
                         className="question-text-on-specific-question">{question?.questionText}</div>
                    <div id="question-submission-time"
                         className="time-stamp-on-specific-question">{question?.submissionTime}</div>
                    <button type="button" onClick={event => {
                        showAndHideCommentField(event)
                    }}>Comment
                    </button>

                </div>
            </div>

            <div id="post-comment-area" className={commentDiv ? "visible" : "hidden"}>
                <form onSubmit={event => {
                    postComment(event)
                }}>
                    <textarea id="comment-text-area" name="comment-text-area" className="reg-fields" rows="3" cols="50"
                              minLength="5"/>
                    <button type="submit" className="reg-fields">Post comment</button>
                </form>
            </div>

            <div id="comments"
                 className="slight-white-background">{question?.commentList && question?.commentList?.map(comment => {
                return (
                    <div key={"comment-id-" + comment?.id} className="laBorder">
                        <div>{comment?.commentText}</div>
                        <button onClick={event => doUpvote(event, comment?.id)} className="up-vote"
                                title="Upvote comment"></button>
                        <span>{comment?.upVoteCount} </span>
                        <button onClick={event => doDownvote(event, comment?.id)} className="down-vote"></button>
                        <span>{comment?.downVoteCount} </span>
                        <span>{comment?.submissionTime}</span>
                        <button onClick={event => deleteComment(event, comment.id)} className="delete-comment-button"></button>
                    </div>
                );
            })}</div>

            <Footer/>
        </div>
    );
}
