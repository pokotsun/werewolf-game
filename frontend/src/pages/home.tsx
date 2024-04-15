import { useState } from 'react';
import '../App.css';

function Home() {
    const [count, setCount] = useState(0);

    return (
        <>
            <div className="main-content background-image">
                <h1 className="title">人狼オンライン</h1>
                <div className="card">
                    <button onClick={() => setCount((count) => count + 1)}>
                        count is {count}
                    </button>
                </div>
            </div>
        </>
    )
}

export default Home
