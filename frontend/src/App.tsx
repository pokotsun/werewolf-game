// import './App.css'
import { Route, BrowserRouter, Routes } from 'react-router-dom'
import Home from './pages/home'
import CreateVillage from './pages/createVillage/createVillage'
import EnterVillage from './pages/enterVillage/enterVillage'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/create-village" element={<CreateVillage />} />
        <Route path="/enter-village" element={<EnterVillage />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
