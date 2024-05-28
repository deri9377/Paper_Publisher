import 'bootstrap/dist/css/bootstrap.min.css';

export default function Footer() {
        const footerStyle = {
            position: "absolute",
            bottom: "0",
            width: "100%",
            height: "2.5rem"
        }
    return (
       <footer style={footerStyle}>made by Devin Riess</footer>
    )
}