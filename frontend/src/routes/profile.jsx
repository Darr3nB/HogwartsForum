import MenuLayout from "../components/MenuLayout.jsx";
import Footer from "../components/Footer.jsx";
import {utility} from "../utility.js";
import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";


async function deleteProfile(e, currentUser, navigate) {
    e.preventDefault();
    const profileData = new FormData(e.currentTarget);

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
    const navigate = useNavigate();
    const [isLoggedIn, setLoginState] = useState(false);
    const [user, setUser] = useState({});

    const isLoggedInRequest = async () => {
        return await utility.apiGet(`/user/logged-in`)
            .then(r => {
                if (r.status === 204) {
                    return false;
                } else if (r.status === 200) {
                    return r.json();
                }
            });
    };

    useEffect(() => {
        isLoggedInRequest().then(
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

    return (
        <div>
            <MenuLayout/>

            <h1 className="header-to-middle">Your profile</h1>
            <div className="card-to-middle-with-border">
                <img alt="Blank profile picture." height="150" width="200" className="reg-fields"/>
                <div id="profile-id-on-profile-page" className="reg-fields">Registration id: {user.id}</div>
                <div id="username-on-profile-page" className="reg-fields">Username: {user.name}</div>
                <div id="house-on-profile-page" className="reg-fields">House: {user.house}</div>
                <div id="pet-on-profile-page" className="reg-fields">Pet type: {user.pet}</div>

                <form onSubmit={event => deleteProfile(event, user, navigate)}>
                    <label htmlFor="password-for-delete-profile" className="reg-fields">Enter password to delete
                        profile:</label>
                    <input type="text" id="password-for-delete-profile" name="password-for-delete-profile"
                           required="required" className="reg-fields"/>
                    <button type="submit" className="button-to-middle">Delete profile</button>
                </form>
            </div>
            <Footer/>
        </div>
    );
}
