import { useNavigate } from "react-router-dom";
import { DEFAULT_BACKGROUND_IMAGE_PATH } from '@/common/constants.ts';
import "./citizen-win-result-container.css";
import werewolfIcon from "/roles/werewolf-icon.jpg"
import hunterIcon from "/roles/hunter-icon.jpg"
import divinerIcon from "/roles/diviner-icon.jpg";
import ResultUserInfo from '@/features/generic/result-user-info/result-user-info.tsx';

function CitizenWinResultContainer() {
    const navigate = useNavigate();

    const backgroundImage = DEFAULT_BACKGROUND_IMAGE_PATH;

    return (
        <div className="main-content" style={{ backgroundImage: `url(${backgroundImage})` }}>
            <h1>CITIZENS WIN!!!!</h1>
            <div className="result-text-box">
                <small>この瞬間に人狼は息絶えました。</small>
                <h2>市民チームの勝利です。</h2>
                <small>勝利したプレイヤーには、10ポイントが加算されます。</small>
            </div>
            <h1>Result</h1>
            <h2 className="team-result-title">Winner: 市民チーム</h2>
            <div className="team-members-container">
                <ResultUserInfo userName="Bob" role="市民" iconPath={divinerIcon} />
                <ResultUserInfo userName="Alice" role="市民" iconPath={hunterIcon} />
                <ResultUserInfo userName="Bobby" role="市民" iconPath={divinerIcon} />
                <ResultUserInfo userName="Som" role="市民" iconPath={divinerIcon} />
            </div>
            <h2 className="team-result-title">Loser: 人狼チーム</h2>
            <div className="team-members-container">
                <ResultUserInfo userName="Bob" role="人狼" iconPath={werewolfIcon} />
            </div>
        </div>
    )
}

export default CitizenWinResultContainer
