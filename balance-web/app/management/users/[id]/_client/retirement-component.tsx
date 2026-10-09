'use client'

import { useState } from "react" // 1. Import useState
import { Button } from "@/components/ui/button"
import { RetirementForm, retirementSchema } from "@/lib/types/management/user.model"
import { executeAction } from "@/lib/utils"
import { zodResolver } from "@hookform/resolvers/zod"
import { UserX, X } from "lucide-react"
import { useForm } from "react-hook-form"

import * as action from "@/lib/service/action/management/user-management.action"
import { Dialog, DialogClose, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle, DialogTrigger } from "@/components/ui/dialog"
import FormsInput from "@/components/forms/forms-input"

export default function RetirementComponent({userId} : {userId : string}) {
    const [show, setShow] = useState(false)
    const form = useForm<RetirementForm>({
        resolver: zodResolver(retirementSchema),
        defaultValues: {
            retireDate: ""
        }
    })

    function onSubmit(data: RetirementForm) {
        executeAction(async () => {
            await action.updateRetirement(userId, data)
        })
        form.reset()
        setShow(false)
    }

    return (
        <Dialog open={show} onOpenChange={setShow} >
            <DialogTrigger render={
                <Button type="button" variant={'destructive'}>
                    <UserX /> Set Retirement
                </Button>
            } />
            <DialogContent>
                <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-4">
                    <DialogHeader>
                        <DialogTitle>Set Retirement</DialogTitle>
                        <DialogDescription>Please enter retire date to set retirement.</DialogDescription>
                    </DialogHeader>
                    
                    <FormsInput control={form.control} name="retireDate" label="Retire Date" type="date" />
                    
                    <DialogFooter>
                        <DialogClose render={
                            <Button type="button" variant={'outline'}>
                                <X /> Cancel
                            </Button>
                        } />
                        <Button type="submit">
                            <UserX /> Set Retirement
                        </Button>
                    </DialogFooter>
                </form>
            </DialogContent>
        </Dialog>
    )
}