// import './App.css'
import { Route, BrowserRouter, Routes } from 'react-router-dom'
import Home from './pages/home/home'
import CreateVillage from './pages/createVillage/createVillage'
import EnterVillage from './pages/enterVillage/enterVillage'
import SettingVillage from './pages/settingVillage/settingVillage'
import WerewolfWinResult from './pages/werewolfWinResult/werewolfWinResult'
import { CitizenWinResult } from './pages/citizen-win-result'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/create-village" element={<CreateVillage />} />
        <Route path="/enter-village" element={<EnterVillage />} />
        <Route path="/setting-village" element={<SettingVillage />} />
        <Route path="/citizen-win-result" element={<CitizenWinResult />} />
        <Route path="werewolf-win-result" element={<WerewolfWinResult />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
