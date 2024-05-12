import { useNavigate } from 'react-router-dom';
import './home-container.css';
import { DEFAULT_BACKGROUND_IMAGE_PATH } from '@/common/constants.ts';

function HomeContainer() {
    const navigate = useNavigate();

    const backgroundImage = DEFAULT_BACKGROUND_IMAGE_PATH;

    return (
        <div className="main-content background-image" style={{ backgroundImage: `url(${backgroundImage})` }}>
            <h1 className="title">人狼オンライン</h1>
            <div className="enter-button-container">
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

export default HomeContainer
