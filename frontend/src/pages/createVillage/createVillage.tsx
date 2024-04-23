import { useLocation, useNavigate } from "react-router-dom";
import React, { useState } from "react";
import './CreateVillage.css';
import { DEFAULT_BACKGROUND_IMAGE_PATH } from '../../Const.ts'

const BACKGROUND_IMAGE_PATH = "public/background-village-entrance.jpg"

function CreateVillage() {
    const location = useLocation();
    const navigate = useNavigate();
    const backgroundImage = (location.pathname === "/create-village") ? BACKGROUND_IMAGE_PATH : DEFAULT_BACKGROUND_IMAGE_PATH;
    const [villageName, setVillageName] = useState('恐ろしい村');
    const [ownerName, setOwnerName] = useState('hogehoge');
    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        navigate("/setting-village");
    }

    return (
        <div className="background-image main-content" style={{ backgroundImage: `url(${backgroundImage})` }}>
            <h1 className="title">村を作る</h1>
            <form onSubmit={handleSubmit}>
                <label>
                    村の名前:
                    <input type="text" value={villageName} onChange={e => setVillageName(e.target.value)} />
                </label>
                <label>
                    あなたの名前:
                    <input type="text" value={ownerName} onChange={e => setOwnerName(e.target.value)} />
                </label>
                <div className="card">
                    <input type="submit" value="作成" />
                    <input type="button" value="戻る" onClick={() => navigate("/")} />
                </div>
            </form>
        </div>
    )
}

export default CreateVillage