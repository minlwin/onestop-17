'use client'

import { useSearchParams } from "next/navigation"
import { useEffect } from "react"
import { toast } from "../ui/toast"

export default function MessageHandler() {

    const searchParams = useSearchParams()

    useEffect(() => {
        const message = searchParams.get("message")

        if(message) {
            toast.add({
                title: "Message",
                description: message
            })
        }
    }, [searchParams])

    return (
        <></>
    )
}