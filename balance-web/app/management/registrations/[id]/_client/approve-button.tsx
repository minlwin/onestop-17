'use client'

import FormsTextarea from "@/components/forms/forms-textarea"
import { Button } from "@/components/ui/button"
import { Dialog, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle, DialogTrigger } from "@/components/ui/dialog"
import { RegistrationStatusForm, registrationStatusSchema } from "@/lib/types"
import { executeAction } from "@/lib/utils"
import { zodResolver } from "@hookform/resolvers/zod"
import { Check } from "lucide-react"
import { useState } from "react"
import { useForm } from "react-hook-form"
import * as action from "@/lib/service/action/management/registration.action"
import FormsInput from "@/components/forms/forms-input"

export default function ApproveButton({id} : {id : string}) {

    const [show, setShow] = useState(false)
    const form = useForm({
        resolver: zodResolver(registrationStatusSchema),
        defaultValues: {
            status: 'Approved',
            remark : ''
        }
    })

    function updateStatus(updateForm : RegistrationStatusForm) {
        executeAction(async () => {
            await action.updateStatus(id, updateForm)
        })
        form.reset()
        setShow(false)
    }

    return (
        <Dialog open={show} onOpenChange={setShow}>

            <DialogTrigger render={
                <Button onClick={() => setShow(true)}>
                    <Check /> Approve
                </Button>        
            } />

            <DialogContent>
                <form onSubmit={form.handleSubmit(updateStatus)}>
                    <DialogHeader>
                        <DialogTitle>Approve Registration</DialogTitle>
                        <DialogDescription>Enter remark if you want to note for approvement.</DialogDescription>
                    </DialogHeader>   
                    <div className="py-4">
                        <FormsInput control={form.control} name="status" type="hidden" />
                        <FormsTextarea control={form.control} name="remark" label="Remark" />
                    </div> 
                    <DialogFooter>
                        <Button type="submit">
                            <Check /> Approve
                        </Button>
                    </DialogFooter>
                </form>
            </DialogContent>            
        </Dialog>
    )
}