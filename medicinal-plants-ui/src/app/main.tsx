import React from 'react'
import ReactDOM from 'react-dom/client'
import { AppRouter } from '@/app/router'
import '@/app/styles/globals.css'

const rootElement = document.getElementById('root')

if (!rootElement) {
    throw new Error("Root element '#root' not found")
}

ReactDOM.createRoot(rootElement).render(
    <React.StrictMode>
        <AppRouter />
    </React.StrictMode>
)