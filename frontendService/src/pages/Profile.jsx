const Profile = () => {

    var loggedIn = localStorage.getItem('loggedIn')
    const user = localStorage.getItem('user')

    return (
        <div>
            <h1>
                Hello {user}, Welcome to your profile.
            </h1>
        </div>
    )
}

export default Profile