import { useNavigate } from "react-router-dom";
import { DEFAULT_BACKGROUND_IMAGE_PATH } from "../../Const";
import "./CitizenWinResult.css";
import userIcon from "/user-icon.jpg";
import divinerIcon from "/roles/diviner-icon.jpg";

function CitizenWinResult() {
    const navigate = useNavigate();

    const backgroundImage = DEFAULT_BACKGROUND_IMAGE_PATH;

    return (
        <>
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
                    <UserInfo userName="Bob" role="市民" />
                    <UserInfo userName="Alice" role="市民" />
                    <UserInfo userName="Bobby" role="市民" />
                    <UserInfo userName="Som" role="市民" />
                </div>
                <h2 className="team-result-title">Loser: 人狼チーム</h2>
                <div className="team-members-container">
                    <UserInfo userName="Bob" role="人狼" />
                </div>
            </div>
        </>
    )
}

const UserInfo = (props: { userName: string, role: string }) => {
    return (
        <div className="user-info-container">
            <img className="user-icon" src={userIcon} />
            <div className="user-role-names">
                <div className="user-info-name">
                    <div>{props.userName}</div>
                    <small>{props.role}</small>
                </div>
                <img className="role-icon" src={divinerIcon} />
            </div>
        </div >
    )
}


export default CitizenWinResult