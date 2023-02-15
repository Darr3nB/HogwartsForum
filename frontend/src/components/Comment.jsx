import {utility} from "../utility.js";
import {useNavigate} from "react-router-dom";

export default function Comment({comment, loggedInUserId, questionId}) {
    const navigate = useNavigate();

    const upVoteComment = async (event, commentId) => {
        event.preventDefault();

        await utility.apiGet(`/api/upvote-comment/${commentId}`)
            .then(response => {
                if (response.ok) {
                    navigate(0);
                }
            });
    }

    const downVoteComment = async (event, commentId) => {
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

        await utility.apiDeleteWithPathData(`/api/delete-comment/${loggedInUserId}/${questionId}/${commentId}`)
            .then(response => {
                if (response.ok) {
                    navigate(0);
                }
            });
    }

    return (
        <div>
            <div><img src={comment.image} alt="Uploaded picture for comment" className="uploaded-picture"/></div>
            <div>{comment?.commentText}</div>
            <button onClick={event => upVoteComment(event, comment?.id)} className="up-vote"
                    title="Upvote comment"></button>
            <span>{comment?.upVoteCount} </span>
            <button onClick={event => downVoteComment(event, comment?.id)} className="down-vote"></button>
            <span>{comment?.downVoteCount} </span>
            <span>{comment?.submissionTime}</span>
            <button onClick={event => deleteComment(event, comment.id)}
                    className="delete-comment-button"></button>
        </div>
    );
}