import { useNavigate } from "react-router-dom";
import { DEFAULT_BACKGROUND_IMAGE_PATH } from '@/common/constants.ts';
import "./werewolf-win-result-container.css";
import wereWolfIcon from "/roles/werewolf-icon.jpg"
import divinerIcon from "/roles/diviner-icon.jpg";
import ResultUserInfo from "@/features/generic/result-user-info/result-user-info";

function CitizenWinResult() {
    const navigate = useNavigate();

    const backgroundImage = DEFAULT_BACKGROUND_IMAGE_PATH;

    return (
        <>
            <div className="main-content" style={{ backgroundImage: `url(${backgroundImage})` }}>
                <h1>CITIZENS WIN!!!!</h1>
                <div className="result-text-box">
                    <small>この瞬間に人狼が人間と同数以上になりました。</small>
                    <h2>人狼チームの勝利です。</h2>
                    <small>勝利したプレイヤーには、10ポイントが加算されます。</small>
                </div>
                <h1>Result</h1>
                <h2 className="team-result-title">Winner: 人狼チーム</h2>
                <div className="team-members-container">
                    <ResultUserInfo userName="Bob" role="人狼" iconPath={wereWolfIcon} />
                </div>
                <h2 className="team-result-title">Loser: 人間チーム</h2>
                <div className="team-members-container">
                    <ResultUserInfo userName="Bob" role="市民" iconPath={divinerIcon} />
                    <ResultUserInfo userName="Alice" role="市民" iconPath={divinerIcon} />
                    <ResultUserInfo userName="Bobby" role="市民" iconPath={divinerIcon} />
                    <ResultUserInfo userName="Som" role="市民" iconPath={divinerIcon} />
                </div>
            </div>
        </>
    )
}

export default CitizenWinResult
