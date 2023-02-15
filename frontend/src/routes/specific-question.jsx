import {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import {utility} from "../utility.js";
import Comment from "../components/Comment";

export default function SpecificQuestion() {
    const {id} = useParams();
    const [question, setQuestionState] = useState(null);
    const [commentDiv, setCommentDivState] = useState(false);
    const [loggedInUSer, setUser] = useState(false);
    const navigate = useNavigate();
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
                <div className="loading-field"></div>
                <div className="slight-white-background">Loading, please wait, the magic is happening.</div>
            </div>
        );
    }

    const showAndHideCommentField = (event) => {
        event.preventDefault();
        setCommentDivState(!commentDiv);
    }

    const postComment = async (event) => {
        event.preventDefault();
        const componentData = new FormData(event.currentTarget);
        const questionPicture = uploadedImage === null ? utility.questionMarkPicture : uploadedImage;

        await utility.apiPostWithDictionaryDataType(
            `/api/post-comment-on-specific-question/${question.id}/${loggedInUSer.id}`, {'commentText': componentData.get("comment-text-area"), 'image': questionPicture}
        )
            .then(response => {
                if (response.ok) {
                    navigate(0);
                }
            })
    }

    const uploadImage = async (event) => {
        const file = event.target.files[0];
        const base64 = await utility.convertBase64(file).then();

        setImage(base64);
    }

    return (
        <div>
            <div className="specific-question-page">

                <div className="slight-white-background">
                    <h2 className="header-to-middle">{question?.title}</h2>
                </div>

                <div className="slight-white-background">
                    <div className="header-to-middle"><img src={question.image} alt="Uploaded picture by user"
                                                           className="uploaded-picture"/></div>
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
                    <div className="header-to-middle"><input type="file" id="input-for-file-on-post-comment" name="input-for-file-on-post-question"
                                                             accept=".jpg, .jpeg, .png" onChange={(event) => {
                        uploadImage(event);
                    }}/></div>
                    <button type="submit" className="reg-fields">Post comment</button>
                </form>
            </div>

            <div id="comments"
                 className="slight-white-background">{question?.commentList && question?.commentList?.map(comment => {
                return (
                    <div key={"comment-id-" + comment?.id} className="laBorder">
                        <Comment comment={comment} loggedInUserId={loggedInUSer.id} questionId={id} upLoadedPicture={uploadedImage}/>
                    </div>
                );
            })}</div>
        </div>
    );
}
