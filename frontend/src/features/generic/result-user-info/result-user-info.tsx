import userIcon from "/user-icon.jpg";
import "./result-user-info.css";

export const ResultUserInfo = (props: { userName: string, role: string, iconPath: string }) => {
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

export default ResultUserInfo
