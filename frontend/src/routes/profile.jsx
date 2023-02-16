import {utility} from "../utility.js";
import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";


export default function Profile() {
    // TODO switch password input type to 'password' type
    const navigate = useNavigate();
    const [user, setUser] = useState({});
    const [showAndHide, setShowAndHide] = useState(false);
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

    const deleteProfile = async (e) => {
        e.preventDefault();
        const profileData = new FormData(e.currentTarget);

        await utility.apiPostWithDictionaryDataType(`/user/delete-profile`, {
            'id': user.id,
            'username': user.name,
            'password': profileData.get('password-for-delete-profile')
        })
            .then(response => {
                if (response.ok) {
                    navigate("/");
                }
            });
    }

    const hideAndShowInputFiled = (event) => {
        event.preventDefault();
        setShowAndHide(!showAndHide);
    }

    const uploadImage = async (event) => {
        const file = event.target.files[0];
        const base64 = await utility.convertBase64(file).then();

        setImage(base64);
    }

    const updatePicture = (event) => {
        event.preventDefault();
        const picture = uploadedImage === null ? utility.questionMarkPicture : uploadedImage;

        utility.apiPostWithDictionaryDataType(`/user/update-profile-picture`,
            {'userId': user.id, 'newPicture': picture})
            .then(response => {
                if (response.ok) {
                    navigate(0);
                }
            })
    }

    return (
        <div>
            <h1 className="header-to-middle">Your profile</h1>

            <div className="card-to-middle-with-border">
                <img src={user.profilePicture} alt="Blank profile picture." height="150" width="200"
                     className="reg-fields"/>
                <button id="upload-profile-picture-button" type="button" className="reg-fields"
                        onClick={event => hideAndShowInputFiled(event)}>Upload new profile
                    picture
                </button>
                <div id="update-picture" className={showAndHide ? "visible" : "hidden"}><input type="file"
                                                                                               id="input-for-file-on-post-comment"
                                                                                               name="input-for-file-on-post-question"
                                                                                               accept=".jpg, .jpeg, .png"
                                                                                               onChange={(event) => {
                                                                                                   uploadImage(event);
                                                                                               }}/>
                    <button onClick={event => updatePicture(event)}>Update Picture</button>
                </div>
                <div id="profile-id-on-profile-page" className="reg-fields">Registration id: {user.id}</div>
                <div id="username-on-profile-page" className="reg-fields">Username: {user.name}</div>
                <div id="house-on-profile-page"
                     className="reg-fields">House: {user.house?.charAt(0).toUpperCase() + user.house?.slice(1).toLowerCase()}</div>
                <div id="pet-on-profile-page" className="reg-fields">Pet
                    type: {user.pet?.charAt(0).toUpperCase() + user.pet?.slice(1).toLowerCase()}</div>
                <div className="reg-fields">Reputation: {user.reputation}</div>

                <form onSubmit={event => deleteProfile(event)}>
                    <label htmlFor="password-for-delete-profile" className="reg-fields">Enter password to delete
                        profile:</label>
                    <input type="text" id="password-for-delete-profile" name="password-for-delete-profile"
                           required="required" className="reg-fields"/>
                    <button type="submit" className="button-to-middle">Delete profile</button>
                </form>
            </div>
        </div>
    );
}
