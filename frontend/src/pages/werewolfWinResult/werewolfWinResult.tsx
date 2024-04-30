import { useNavigate } from "react-router-dom";
import { DEFAULT_BACKGROUND_IMAGE_PATH } from '@/common/constants.ts';
import "./WerewolfWinResult.css";
import userIcon from "/user-icon.jpg";
import wereWolfIcon from "/roles/werewolf-icon.jpg"
import divinerIcon from "/roles/diviner-icon.jpg";

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
                    <UserInfo userName="Bob" role="人狼" iconPath={wereWolfIcon} />
                </div>
                <h2 className="team-result-title">Loser: 人間チーム</h2>
                <div className="team-members-container">
                    <UserInfo userName="Bob" role="市民" iconPath={divinerIcon} />
                    <UserInfo userName="Alice" role="市民" iconPath={divinerIcon} />
                    <UserInfo userName="Bobby" role="市民" iconPath={divinerIcon} />
                    <UserInfo userName="Som" role="市民" iconPath={divinerIcon} />
                </div>
            </div>
        </>
    )
}

const UserInfo = (props: { userName: string, role: string, iconPath: string }) => {
    return (
        <div className="user-info-container">
            <img className="user-icon" src={userIcon} />
            <div className="user-role-names">
                <div className="user-info-name">
                    <div>{props.userName}</div>
                    <small>{props.role}</small>
                </div>
                <img className="role-icon" src={props.iconPath} />
            </div>
        </div >
    )
}


export default CitizenWinResult
