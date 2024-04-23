// import './App.css'
import { Route, BrowserRouter, Routes } from 'react-router-dom'
import Home from './pages/home/home'
import CreateVillage from './pages/createVillage/createVillage'
import EnterVillage from './pages/enterVillage/enterVillage'
import SettingVillage from './pages/settingVillage/settingVillage'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/create-village" element={<CreateVillage />} />
        <Route path="/enter-village" element={<EnterVillage />} />
        <Route path="/setting-village" element={<SettingVillage />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
