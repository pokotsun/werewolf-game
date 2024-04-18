import { useNavigate } from 'react-router-dom';
import '../App.css';

function Home() {
    const navigate = useNavigate();

    return (
        <div className="main-content background-image">
            <h1 className="title">人狼オンライン</h1>
            <div className="card">
                <button onClick={() => navigate("/create-village")}>
                    村を作る
                </button>
                <button onClick={() => navigate("/enter-village")}>
                    村に入る
                </button>
            </div>
        </div>
    )
}

export default Home
