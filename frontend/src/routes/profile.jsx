import {utility} from "../utility.js";
import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";


export default function Profile() {
    // TODO switch password input type to 'password' type
    const navigate = useNavigate();
    const [user, setUser] = useState({});

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

    const deleteProfile = async (e, user) => {
        e.preventDefault();
        const profileData = new FormData(e.currentTarget);

        await utility.apiPostWithDictionaryDataType(`delete-profile`, {
            'id': user.id,
            'username': user.username,
            'password': profileData.get('password-for-delete-profile')
        })
            .then(response => {
                if (response.ok) {
                    navigate("/");
                }
            });
    }

    return (
        <div>
            <h1 className="header-to-middle">Your profile</h1>

            <div className="card-to-middle-with-border">
                <img src={user.profilePicture} alt="Blank profile picture." height="150" width="200"
                     className="reg-fields"/>
                <button id="upload-profile-picture-button" type="button" className="reg-fields">Upload new profile
                    picture
                </button>
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
