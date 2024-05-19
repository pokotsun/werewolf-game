// import './App.css'
import { Route, BrowserRouter, Routes } from 'react-router-dom'
import { Home } from './pages/home'
import SettingVillage from './pages/settingVillage/settingVillage'
import WerewolfWinResult from './features/werewolf-win-result/werewolf-win-result-container'
import { CitizenWinResult } from './pages/citizen-win-result'
import { CreateVillage } from './pages/create-village'
import { EnterVillage } from './pages/enter-village'

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
