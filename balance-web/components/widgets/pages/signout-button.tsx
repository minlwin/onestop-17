'use client'

import { Button } from "@/components/ui/button"
import { executeAction } from "@/lib/utils"
import { LogOut } from "lucide-react"
import * as action from "@/lib/service/action/signout-action"

export default function SignOutButton() {

    function onSignOut() {
        executeAction(async () => {
            await action.signOut()
        })
    }

    return (
        <Button variant={'outline'} onClick={onSignOut}>
            <LogOut /> Sign Out
        </Button>
    )
}