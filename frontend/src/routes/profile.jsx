import MenuLayout from "../components/MenuLayout.jsx";
import Footer from "../components/Footer.jsx";
import {utility} from "../utility.js";
import {useState} from "react";
import {useNavigate} from "react-router-dom";


async function deleteProfile(e, currentUser) {
    e.preventDefault();
    const profileData = new FormData(e.currentTarget);
    const navigate = useNavigate();

    await utility.apiPostWithDictionaryDataType(`delete-profile`, {
        'id': currentUser.id,
        'username': currentUser.username,
        'password': profileData.get('password-for-delete-profile')
    })
        .then(response => {
            if (response.ok) {
                navigate("/");
            }
        });
}

export default function Profile() {
    // TODO if not logged in redirect to error
    // TODO switch password input type to 'password'
    // TODO get current user's data instead of dummy data
    const [user] = useState({
        'id': 1,
        'username': "Harry Potter",
        'houseType': "Gryffindor",
        'petType': 'Owl'
    });

    return (
        <div>
            <MenuLayout/>

            <img alt="Blank profile picture." height="150" width="200"/>
            <div id="profile-id-on-profile-page">{user.id}</div>
            <div id="username-on-profile-page">{user.username}</div>
            <div id="house-on-profile-page">{user.houseType}</div>
            <div id="pet-on-profile-page">{user.petType}</div>

            <form onSubmit={event => deleteProfile(event, user)}>
                <label htmlFor="password-for-delete-profile">Enter password to delete profile:</label>
                <input type="text" id="password-for-delete-profile" name="password-for-delete-profile"
                       required="required"/>
                <button type="submit">Delete my profile</button>
            </form>

            <Footer/>
        </div>
    );
}
