// import './App.css'
import { Route, BrowserRouter, Routes } from 'react-router-dom'
import Home from './pages/home'
import CreateVillage from './pages/createVillage'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/create-village" element={<CreateVillage />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
