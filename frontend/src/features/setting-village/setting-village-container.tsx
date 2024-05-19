import { useLocation, useNavigate } from 'react-router-dom';
import './setting-vllage-container.css'
import { DEFAULT_BACKGROUND_IMAGE_PATH } from '@/common/constants.ts';
import werewolfIcon from '/roles/werewolf-icon.jpg';

const BACKGROUND_IMAGE_PATH = "/background-village-entrance.jpg"

function SettingVillageContainer() {
    const location = useLocation();
    const navigate = useNavigate();

    const backgroundImage = (location.pathname == '/setting-village') ? BACKGROUND_IMAGE_PATH : DEFAULT_BACKGROUND_IMAGE_PATH;

    return (
        <>
            <div className="main-content" style={{ backgroundImage: `url(${backgroundImage})` }}>
                <div className="village-settings-container">
                    <div className="role-settings-container">
                        <h2>ルールの設定</h2>
                        <p>あなたはゲームマスターです。<br />
                            ゲームマスターのみゲームの設定を行えます。<br />
                            デフォルト設定から変更する場合は以下から設定を行なってください。<br />
                            ルールの設定が完了したらゲーム開始を押してゲームを開始してください。
                        </p>
                        <h3>配役カード設定</h3>
                        <div className="role-select">
                            <div>
                                <img className="role-icon" src={werewolfIcon}></img>
                            </div>
                            <div className="role-description-container">
                                <h3>人狼</h3>
                                <RoleDescription label="チーム名" value="人狼チーム" />
                                <RoleDescription label="人数カウント" value="人狼" />
                                <RoleDescription label="占い&霊媒結果" value="人狼" />
                                <RoleDescription label="勝利条件" value="人間を人狼の数以下にする" />
                            </div>
                        </div>
                    </div>
                    <div className="participants-info-container">
                        <h2>参加者リスト</h2>
                        <p>4人のプレイヤーが参加しています。</p>
                        <ol>
                            <li>Bob</li>
                            <li>Tom</li>
                            <li>Mary</li>
                            <li>Ann</li>
                        </ol>
                    </div>
                </div>
                <div>
                    <button onClick={() => navigate("/citizen-win-result")}> ゲームを開始する </button>
                </div>
            </div >
        </>
    )
}



export const RoleDescription = (props: { label: string, value: string }) => {
    return (
        <>
            <small>{props.label}</small>
            <div>{props.value}</div>
        </>
    )
}

export default SettingVillageContainer
