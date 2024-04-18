import { useNavigate } from "react-router-dom";
import React, { useState } from "react";
import '../CreateVillage.css';

function EnterVillage() {
    const navigate = useNavigate();
    const [villageName, setVillageName] = useState('恐ろしい村');
    const [ownerName, setOwnerName] = useState('hogehoge');
    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        navigate("/village");
    }

    return (
        <div className="background-image main-content">
            <h1 className="title">村に入る</h1>
            <form onSubmit={handleSubmit}>
                <label>
                    村 ID:
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

export default EnterVillage