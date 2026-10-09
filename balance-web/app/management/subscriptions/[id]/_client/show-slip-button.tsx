'use client'

import { Button } from "@/components/ui/button"
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger } from "@/components/ui/dialog"
import { File } from "lucide-react"
import Image from "next/image"

export default function ShowSlipButton({slip} : {slip : string}) {
    return (
        <Dialog>
            <DialogTrigger render={
                <Button variant={'outline'}>
                    <File /> Show Slip
                </Button>
            } />

            <DialogContent>
                <DialogHeader>
                    <DialogTitle>
                        Payment Slip
                    </DialogTitle>
                </DialogHeader>

                <Image src={'/slip.png'} width={400} height={400} alt="Payment Slip" />
            </DialogContent>
        </Dialog>
    )
}